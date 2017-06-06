/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Lucas
 */
public class GraficoAvaliacao {
    
    private int notaPositiva;
    private int notaNegativa;
    private int notaImparcial;


    public GraficoAvaliacao() {
    }

    public GraficoAvaliacao(int notaPositiva, int notaNegativa, int notaImparcial) {
        this.notaPositiva = notaPositiva;
        this.notaNegativa = notaNegativa;
        this.notaImparcial = notaImparcial;
    }

    public int getNotaPositiva() {
        return notaPositiva;
    }

    public void setNotaPositiva(int notaPositiva) {
        this.notaPositiva = notaPositiva;
    }

    public int getNotaNegativa() {
        return notaNegativa;
    }

    public void setNotaNegativa(int notaNegativa) {
        this.notaNegativa = notaNegativa;
    }

    public int getNotaImparcial() {
        return notaImparcial;
    }

    public void setNotaImparcial(int notaImparcial) {
        this.notaImparcial = notaImparcial;
    }
    
    
    
    
}
