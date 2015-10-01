package com.polarion.starter.model;

import android.util.JsonReader;

import java.io.IOException;

/**
 * Created by scottrichards on 9/20/15.
 */
public class SerializeAgendaItem {

    public AgendaItem readItem(JsonReader reader) throws IOException {
        long id = -1;
        String text = null;
        AgendaItem agendaItem = new AgendaItem();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("session")) {
                agendaItem.setSessionName(reader.nextString());
            } else if (name.equals("description")) {
                agendaItem.setDescription(reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return agendaItem;
    }
}
