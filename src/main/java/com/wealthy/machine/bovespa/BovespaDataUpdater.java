package com.wealthy.machine.bovespa;

import com.wealthy.machine.bovespa.dataaccess.BovespaDailyQuoteDataAccess;
import com.wealthy.machine.bovespa.dataaccess.BovespaShareCodeDataAccess;
import com.wealthy.machine.bovespa.dataaccess.BovespaUrlDataAccess;
import com.wealthy.machine.bovespa.seeker.BovespaDataSeeker;
import com.wealthy.machine.core.Config;
import com.wealthy.machine.core.DataUpdater;
import com.wealthy.machine.core.util.data.JsonDataFileHandler;

import java.util.stream.Collectors;

public class BovespaDataUpdater implements DataUpdater {

	@Override
	public void execute(JsonDataFileHandler jsonDataFile, Config config) {
		var logger = config.getLogger(BovespaDataUpdater.class);
		var dataSeeker = new BovespaDataSeeker();
		var dailyQuoteDataAccess = new BovespaDailyQuoteDataAccess(jsonDataFile);
		var shareCodeDataAccess = new BovespaShareCodeDataAccess(jsonDataFile);
		var urlDataAccess = new BovespaUrlDataAccess(jsonDataFile, config.getInitialYear(), config.getDataPath());
		var missingUrls = urlDataAccess.listMissingUrl();
		var urlsDownloaded = missingUrls
				.stream()
				.peek(url -> {
					logger.info("Starting download url={}", url);
					var dataSet = dataSeeker.read(url);
					dailyQuoteDataAccess.save(dataSet);
					logger.info("Download completed url={}", url);
				})
				.collect(Collectors.toUnmodifiableSet());
		urlDataAccess.save(urlsDownloaded);
		var downloadedShareCodes = dailyQuoteDataAccess.listDownloadedShareCode();
		shareCodeDataAccess.save(downloadedShareCodes);
	}

}
