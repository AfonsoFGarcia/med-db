package pt.ist.sirs.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class MedDBRoot_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static dml.runtime.RoleMany<pt.ist.sirs.domain.MedDBRoot,pt.ist.sirs.domain.MedDBCommon> role$$object = new dml.runtime.RoleMany<pt.ist.sirs.domain.MedDBRoot,pt.ist.sirs.domain.MedDBCommon>() {
        public dml.runtime.RelationBaseSet<pt.ist.sirs.domain.MedDBCommon> getSet(pt.ist.sirs.domain.MedDBRoot o1) {
            return ((MedDBRoot_Base)o1).get$rl$object();
        }
        public dml.runtime.Role<pt.ist.sirs.domain.MedDBCommon,pt.ist.sirs.domain.MedDBRoot> getInverseRole() {
            return pt.ist.sirs.domain.MedDBCommon.role$$root;
        }
        
    };
    public static dml.runtime.Relation<pt.ist.sirs.domain.MedDBRoot,pt.ist.sirs.domain.MedDBCommon> Root;
    
    
    private RelationList<pt.ist.sirs.domain.MedDBRoot,pt.ist.sirs.domain.MedDBCommon> get$rl$object() {
        return get$$relationList("object", Root);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  MedDBRoot_Base() {
        super();
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
    
    public int getObjectCount() {
        return get$rl$object().size();
    }
    
    public boolean hasAnyObject() {
        return (! get$rl$object().isEmpty());
    }
    
    public boolean hasObject(pt.ist.sirs.domain.MedDBCommon object) {
        return get$rl$object().contains(object);
    }
    
    public java.util.Set<pt.ist.sirs.domain.MedDBCommon> getObjectSet() {
        return get$rl$object();
    }
    
    public void addObject(pt.ist.sirs.domain.MedDBCommon object) {
        Root.add((pt.ist.sirs.domain.MedDBRoot)this, object);
    }
    
    public void removeObject(pt.ist.sirs.domain.MedDBCommon object) {
        Root.remove((pt.ist.sirs.domain.MedDBRoot)this, object);
    }
    
    public java.util.List<pt.ist.sirs.domain.MedDBCommon> getObject() {
        return get$rl$object();
    }
    
    public void set$object(OJBFunctionalSetWrapper object) {
        get$rl$object().setFromOJB(this, "object", object);
    }
    
    public java.util.Iterator<pt.ist.sirs.domain.MedDBCommon> getObjectIterator() {
        return get$rl$object().iterator();
    }
    
    protected void checkDisconnected() {
        if (hasAnyObject()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$objectId(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "OBJECT_ID"), state);
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("object")) return Root;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("object", Root);
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private java.lang.Integer objectId;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.objectId = this.objectId;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.Integer objectId;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.objectId = obj.objectId;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.objectId = this.objectId;
                
            }
            
        }
        
    }
    
}
