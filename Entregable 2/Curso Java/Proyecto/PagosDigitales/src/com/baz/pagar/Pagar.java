package com.baz.pagar;

import com.baz.notificaciones.Push;

import javax.swing.*;

public class Pagar {

    public static void pagar(Push push, boolean pagado) {
        if (pagado) {
            System.out.println("El pago se realizo correctamente a: " + push.getNombreBeneficiario());
            JOptionPane.showMessageDialog(null, "Operacion realizada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Se rechazo el pago", "Rechazo", JOptionPane.ERROR_MESSAGE);
            System.out.println("Se rechazo el pago");
        }
    }
}
