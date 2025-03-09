package br.dev.malyson.arcade.enums;

public enum Tier {
    FREE(0),
    BASIC(500 * 1024 * 1024), // 500MB em bytes
    PREMIUM(2 * 1024 * 1024 * 1024); // 2GB em bytes

    private final int storageBytes;

    Tier(int storageBytes) {
        this.storageBytes = storageBytes;
    }

    public int getStorageBytes() {
        return storageBytes;
    }
}
