package pt.ist.sirs.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class MedBDRoot_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    
    
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  MedBDRoot_Base() {
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
    
    protected void checkDisconnected() {
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$objectId(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "OBJECT_ID"), state);
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
