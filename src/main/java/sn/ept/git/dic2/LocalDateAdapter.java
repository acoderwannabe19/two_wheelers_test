package sn.ept.git.dic2;

import java.time.LocalDate;
import jakarta.json.bind.adapter.JsonbAdapter;

public class LocalDateAdapter implements JsonbAdapter<LocalDate, String> {
    @Override
    public String adaptToJson(LocalDate date) throws Exception {
        return date.toString();
    }

    @Override
    public LocalDate adaptFromJson(String dateStr) throws Exception {
        return LocalDate.parse(dateStr);
    }
}

