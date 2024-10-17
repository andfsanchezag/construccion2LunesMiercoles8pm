/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ESTUDIANTE
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String document;
    private String age;
    private String userName;
    private String password;
}
