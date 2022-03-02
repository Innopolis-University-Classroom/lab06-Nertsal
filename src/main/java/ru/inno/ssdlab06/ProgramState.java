package ru.inno.ssdlab06;

import java.util.Optional;

public class ProgramState {
    private Currency currencyFrom;
    private Currency currencyTo;
    private float currencyValue;
    private final CurrencyRateTable currencyRateTable = new CurrencyRateTable();

    public ProgramState() {}

    public Optional<String> Interact(String inputLine) {
        String[] arguments = inputLine.split(" ");
        if (arguments.length == 0) {
            return Optional.empty();
        }

        switch (arguments[0]) {
            case "exit":
                System.out.println("Exiting...");
                System.exit(0);
            case "convert":
                return Convert(arguments);
            case "from":
                return SetFrom(arguments);
            case "to":
                return SetTo(arguments);
            case "value":
                return SetValue(arguments);
            default:
                break;
        }

        return Optional.empty();
    }

    private Optional<String> SetFrom(String[] arguments) {
        if (arguments.length != 2) {
            return Optional.empty();
        }
        currencyFrom = new Currency(arguments[1]);
        return Optional.of("Set to convert from: " + currencyFrom.name);
    }

    private Optional<String> SetTo(String[] arguments) {
        if (arguments.length != 2) {
            return Optional.empty();
        }
        currencyTo = new Currency(arguments[1]);
        return Optional.of("Set to convert to: " + currencyTo.name);
    }

    private Optional<String> SetValue(String[] arguments) {
        if (arguments.length != 2) {
            return Optional.empty();
        }
        try {
            currencyValue = Float.parseFloat(arguments[1]);
        } catch (NumberFormatException err) {
            return Optional.of("Failed to read value as number");
        }
        return Optional.of("Set currency value to: " + currencyValue);
    }

    private Optional<String> Convert(String[] arguments) {
        if (arguments.length == 1) {
            return ConvertCurrency();
        }

        if (arguments.length != 4) {
            return Optional.empty();
        }
        Currency currencyFrom = new Currency(arguments[1]);
        Currency currencyTo = new Currency(arguments[3]);
        float currencyValue;
        try {
            currencyValue = Float.parseFloat(arguments[2]);
        } catch (NumberFormatException err) {
            return Optional.of("Failed to read value as number");
        }
        return ConvertCurrency(currencyFrom, currencyTo, currencyValue);
    }

    private Optional<String> ConvertCurrency() {
        return ConvertCurrency(currencyFrom, currencyTo, currencyValue);
    }

    private Optional<String> ConvertCurrency(Currency from, Currency to, float value) {
        float rate = currencyRateTable.GetRate(from, to);
        float result = value * rate;
        return Optional.of(value + " in " + from.name + " is " + result + " in " + to.name);
    }
}
