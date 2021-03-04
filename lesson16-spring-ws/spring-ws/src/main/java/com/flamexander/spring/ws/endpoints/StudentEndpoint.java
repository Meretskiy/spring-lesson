package com.flamexander.spring.ws.endpoints;

import com.flamexander.spring.ws.services.StudentService;
import com.flamexander.spring.ws.soap.students.GetAllStudentsRequest;
import com.flamexander.spring.ws.soap.students.GetAllStudentsResponse;
import com.flamexander.spring.ws.soap.students.GetStudentByNameRequest;
import com.flamexander.spring.ws.soap.students.GetStudentByNameResponse;
import com.flamexander.spring.ws.soap.students.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

//ставим аннотацию эндпоинта
@Endpoint
@RequiredArgsConstructor
public class StudentEndpoint {
    //указываем какой неймспейс для студентов использовать
    private static final String NAMESPACE_URI = "http://www.flamexander.com/spring/ws/students";
    private final StudentService studentService;

    //получение запроса студента по имени
    //если нам приходит getStudentByNameRequest (в запросе(PayloadRoot) будет объект типа getStudentByNameRequest )
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByNameRequest")
    //то в ответе мы отправим объект ResponsePayload (в ответе ResponsePayload будет объект типа GetStudentByNameResponse)
    @ResponsePayload
    //так как мы знаем, что нам придет getStudentByNameRequest, то из xml документа выдергивается набор данных,
    //по ним собирается объект и инжектится в метод
    public GetStudentByNameResponse getStudentByName(@RequestPayload GetStudentByNameRequest request) {
        //получаем request, формируем response, из request достаем имя студента, по нему через сервис находим студента
        //и студента заворачиваем в response
        GetStudentByNameResponse response = new GetStudentByNameResponse();
        response.setStudent(studentService.getByName(request.getName()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.flamexander.com/spring/ws/students">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllStudentsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllStudentsRequest")
    @ResponsePayload
    public GetAllStudentsResponse getAllStudents(@RequestPayload GetAllStudentsRequest request) {
        GetAllStudentsResponse response = new GetAllStudentsResponse();
        studentService.getAllStudents().forEach(response.getStudents()::add);
        return response;
    }
}
