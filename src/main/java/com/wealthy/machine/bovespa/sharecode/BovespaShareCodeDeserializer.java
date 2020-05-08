package com.wealthy.machine.bovespa.sharecode;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class BovespaShareCodeDeserializer extends StdDeserializer<BovespaShareCode> {
	public BovespaShareCodeDeserializer() {
		super(BovespaShareCode.class);
	}

	@Override
	public BovespaShareCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return new BovespaShareCode(p.getText());
	}
}