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
public class AreaServico {
    private int idAreaServico;
    private String descricaoAreaServico;

    public AreaServico() {
    }

    public AreaServico(int idAreaServico, String descricaoAreaServico) {
        this.idAreaServico = idAreaServico;
        this.descricaoAreaServico = descricaoAreaServico;
    }

    public int getIdAreaServico() {
        return idAreaServico;
    }

    public void setIdAreaServico(int idAreaServico) {
        this.idAreaServico = idAreaServico;
    }

    public String getDescricaoAreaServico() {
        return descricaoAreaServico;
    }

    public void setDescricaoAreaServico(String descricaoAreaServico) {
        this.descricaoAreaServico = descricaoAreaServico;
    }
    
    
    
}
