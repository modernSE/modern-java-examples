package de.cas.labs.lang.sealed;

// If we remove permits here it is no longer valid as they are in different compilation units
sealed public interface Command permits LoginCommand {
}
