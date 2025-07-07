package Sistema_de_Gerenciamento_de_Bilheteria;

import java.lang.Math;
import java.time.*;

public enum StatusIngresso {
    VALIDADO,
    CANCELADO,
    PENDENTE
}

public class Ingresso {
    private int idIngresso;
    private Time dataHoraVenda;
    private StatusIngresso statusIngresso;

    public Boolean validarNoEvento(int idIngresso){
        if (this.statusIngresso == StatusIngresso.PENDENTE){ 
            this.statusIngresso = StatusIngresso.VALIDADO;
            return true;
        }
        return false; 
    }

    public void cancelar(){
        this.statusIngresso = statusIngresso.CANCELADO;
    }
    public int gerarCodigoUnico(){
        this.idIngresso = (int) (Math.random() * 1000000);
        return this.idIngresso;
    }

    @Override
    public String toString() {
        return "Ingresso ID: " + idIngresso + ", Horario: " + dataHoraVenda + ", Status: " + statusIngresso;
    }
}
