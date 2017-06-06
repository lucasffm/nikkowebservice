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
public class MediaAvaliacao {
    
    private Float mediaCozinha;
    private Float mediaAtendimento;
    private Float mediaComida;
    private Float mediaAmbiente;

    public MediaAvaliacao() {
    }

    public MediaAvaliacao(Float mediaCozinha, Float mediaAtendimento, Float mediaComida, Float mediaAmbiente) {
        this.mediaCozinha = mediaCozinha;
        this.mediaAtendimento = mediaAtendimento;
        this.mediaComida = mediaComida;
        this.mediaAmbiente = mediaAmbiente;
    }

    public Float getMediaCozinha() {
        return mediaCozinha;
    }

    public void setMediaCozinha(Float mediaCozinha) {
        this.mediaCozinha = mediaCozinha;
    }

    public Float getMediaAtendimento() {
        return mediaAtendimento;
    }

    public void setMediaAtendimento(Float mediaAtendimento) {
        this.mediaAtendimento = mediaAtendimento;
    }

    public Float getMediaComida() {
        return mediaComida;
    }

    public void setMediaComida(Float mediaComida) {
        this.mediaComida = mediaComida;
    }

    public Float getMediaAmbiente() {
        return mediaAmbiente;
    }

    public void setMediaAmbiente(Float mediaAmbiente) {
        this.mediaAmbiente = mediaAmbiente;
    }
    
    
    
}
