package ru.javakira.rucscheduleapi.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.javakira.rucscheduleapi.parser.record.Card;

import java.util.List;

@Data
@AllArgsConstructor
public class Cards {
    private List<Card> list;
}
