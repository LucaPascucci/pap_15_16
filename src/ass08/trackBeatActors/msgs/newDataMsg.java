package ass08.trackBeatActors.msgs;


import ass08.trackBeatActors.model.ComplexData;

/**
 * Created by Luca on 27/06/16.
 */

public class NewDataMsg {

    private ComplexData complexData;

    //Utilizzato dal modello per continuare la creazione di dati
    public NewDataMsg(){}

    //Utilizzato per il passaggio dei dati nel MVC
    public NewDataMsg(ComplexData data){
        this.complexData = data;
    }

    public ComplexData getComplexData(){
        return this.complexData;
    }
}
