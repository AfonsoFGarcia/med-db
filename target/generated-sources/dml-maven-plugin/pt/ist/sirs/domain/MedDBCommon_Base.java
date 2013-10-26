package pt.ist.sirs.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class MedDBCommon_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sirs.domain.MedDBCommon,pt.ist.sirs.domain.MedDBRoot> role$$root = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sirs.domain.MedDBCommon,pt.ist.sirs.domain.MedDBRoot>() {
        public pt.ist.sirs.domain.MedDBRoot getValue(pt.ist.sirs.domain.MedDBCommon o1) {
            return ((MedDBCommon_Base.DO_State)o1.get$obj$state(false)).root;
        }
        public void setValue(pt.ist.sirs.domain.MedDBCommon o1, pt.ist.sirs.domain.MedDBRoot o2) {
            ((MedDBCommon_Base.DO_State)o1.get$obj$state(true)).root = o2;
        }
        public dml.runtime.Role<pt.ist.sirs.domain.MedDBRoot,pt.ist.sirs.domain.MedDBCommon> getInverseRole() {
            return pt.ist.sirs.domain.MedDBRoot.role$$object;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sirs.domain.MedDBCommon,pt.ist.sirs.domain.MedDBRoot> Root = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sirs.domain.MedDBCommon,pt.ist.sirs.domain.MedDBRoot>(role$$root);
    static {
        pt.ist.sirs.domain.MedDBRoot.Root = Root.getInverseRelation();
    }
    
    static {
        Root.setRelationName("pt.ist.sirs.domain.MedDBCommon.Root");
    }
    
    
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  MedDBCommon_Base() {
        super();
    }
    
    public pt.ist.sirs.domain.ObjectType getType() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "type");
        return ((DO_State)this.get$obj$state(false)).type;
    }
    
    public void setType(pt.ist.sirs.domain.ObjectType type) {
        ((DO_State)this.get$obj$state(true)).type = type;
    }
    
    private java.lang.String get$type() {
        pt.ist.sirs.domain.ObjectType value = ((DO_State)this.get$obj$state(false)).type;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForEnum(value);
    }
    
    private final void set$type(pt.ist.sirs.domain.ObjectType arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).type = (pt.ist.sirs.domain.ObjectType)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.Integer getObjectId() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "objectId");
        return ((DO_State)this.get$obj$state(false)).objectId;
    }
    
    public void setObjectId(java.lang.Integer objectId) {
        ((DO_State)this.get$obj$state(true)).objectId = objectId;
    }
    
    private java.lang.Integer get$objectId() {
        java.lang.Integer value = ((DO_State)this.get$obj$state(false)).objectId;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForInteger(value);
    }
    
    private final void set$objectId(java.lang.Integer arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).objectId = (java.lang.Integer)((arg0 == null) ? null : arg0);
    }
    
    public pt.ist.sirs.domain.MedDBRoot getRoot() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "root");
        return ((DO_State)this.get$obj$state(false)).root;
    }
    
    public void setRoot(pt.ist.sirs.domain.MedDBRoot root) {
        Root.add((pt.ist.sirs.domain.MedDBCommon)this, root);
    }
    
    public boolean hasRoot() {
        return (getRoot() != null);
    }
    
    public void removeRoot() {
        setRoot(null);
    }
    
    private java.lang.Long get$oidRoot() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).root;
        return (value == null) ? null : value.getOid();
    }
    
    protected void checkDisconnected() {
        if (hasRoot()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$type(pt.ist.fenixframework.pstm.ResultSetReader.readEnum(pt.ist.sirs.domain.ObjectType.class, rs, "TYPE"), state);
        set$objectId(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "OBJECT_ID"), state);
        castedState.root = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_ROOT");
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private pt.ist.sirs.domain.ObjectType type;
        private java.lang.Integer objectId;
        private pt.ist.sirs.domain.MedDBRoot root;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.type = this.type;
            newCasted.objectId = this.objectId;
            newCasted.root = this.root;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private pt.ist.sirs.domain.ObjectType type;
            private java.lang.Integer objectId;
            private pt.ist.sirs.domain.MedDBRoot root;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.type = obj.type;
                this.objectId = obj.objectId;
                this.root = obj.root;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.type = this.type;
                state.objectId = this.objectId;
                state.root = this.root;
                
            }
            
        }
        
    }
    
}
