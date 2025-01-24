package gdse71.project.animalhospital.bo;

import gdse71.project.animalhospital.bo.Custom.PetBO;
import gdse71.project.animalhospital.bo.Custom.impl.PetBOImpl;
import gdse71.project.animalhospital.bo.Custom.impl.PetRecordBOImpl;

public class BOFactory{
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getInstance(){
        if(boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOType{
        PET,PETRECORD
    }
    public SuperBO getBO(BOType type){
        switch (type){
            case PET:
                return new PetBOImpl();
                case PETRECORD:
                    return new PetRecordBOImpl();
                default:
                    return null;
        }
    }
}
