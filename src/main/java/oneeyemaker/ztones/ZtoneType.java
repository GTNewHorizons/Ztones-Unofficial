package oneeyemaker.ztones;

public enum ZtoneType {

    Agon,
    Azur,
    Bitt,
    Cray,
    Fort,
    Glaxx,
    Iszm,
    Jelt,
    Korp,
    Kryp,
    Lair,
    Lave,
    Mint,
    Myst,
    Reds,
    Reed,
    Roen,
    Sols,
    Sync,
    Tank,
    Test,
    Tile,
    Vect,
    Vena,
    Zane,
    Zech,
    Zest,
    Zeta,
    Zion,
    Zkul,
    Zoea,
    Zome,
    Zone,
    Zorg,
    Ztyl,
    Zyth;

    public static final int Variants = 16;

    private boolean isEnabled;

    ZtoneType() {
        this.isEnabled = true;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isGlassLike() {
        return this == Glaxx;
    }

    @Override
    public String toString() {
        return this.name()
            .toLowerCase();
    }
}
