package pt.ist.sirs.domain;

public class MedDBCommon extends MedDBCommon_Base {

    public MedDBCommon() {
        super();
    }

    public MedDBCommon(MedDBRoot root) {
        super();
        this.setObjectId(root.getNewObjectId());
        this.setRoot(root);
        root.addObject(this);
    }
}
