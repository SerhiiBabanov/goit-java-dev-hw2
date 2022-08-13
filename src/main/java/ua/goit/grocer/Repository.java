package ua.goit.grocer;

import java.util.Optional;

public interface Repository {
    Optional<Item> getItem(String code);
}
