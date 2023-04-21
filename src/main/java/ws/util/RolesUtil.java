/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.util;

import java.util.Arrays;

public class RolesUtil {
    public static boolean isRolValido(String roles, String rol) {
        return Arrays.stream(roles.split(",")) //recorre la cadena de texto 'roles' y los separa por comas.
                .anyMatch(r -> r.trim().equalsIgnoreCase(rol)); //valida si el 'rol' existe en la cadena de texto 'roles'
    }
}
