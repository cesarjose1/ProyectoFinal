package com.baz;

import com.baz.notificaciones.Enviar;
import com.baz.notificaciones.Push;
import com.baz.pagar.Pagar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Main {
    public static void main(String[] args) {
        //Se crea una push para ser envia a la persona que va a pagar
        Push push= new Push();
        push.setReferencia("Comida");
        push.setMonto(200);
        push.setNombreBeneficiario("Francisco");
        push.setNombrePagador("Jose");

        //Se modifica el valor del nombre beneficiario para enmascararlo en la push
        String nomBeneficiario= obtenerNomBeneficiarioConReflexion(push,"getNombreBeneficiario");
        push.enmascararNomBeneficiario(nomBeneficiario);

        //Se envia la push y con base a la respuesta validamos si se paga o se rechaza la transaccion
        Enviar enviar = new Enviar();
        boolean pagado=enviar.enviarPush(push);

        //Se valida si se paga o se rechaza el pago
        Pagar.pagar(push,pagado);

    }

    public static String obtenerNomBeneficiarioConReflexion(Push p, String metodo) {
        try {
            Class<? extends Push> extraerInformacionPush = p.getClass();
            Method method = extraerInformacionPush.getMethod(metodo);
            return (String) method.invoke(p);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("No se encontro metodo: " + e.getMessage());
        }
        return "";
    }
}