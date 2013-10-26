package pt.ist.sirs.domain;

public class MedDBRoot extends MedDBRoot_Base {

    public MedDBRoot() {
        super();
    }

    public Integer getNewObjectId() {
        Integer newObjectId = this.getObjectId();
        this.setObjectId(++newObjectId);
        return newObjectId;
    }

}
