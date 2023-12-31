package tests.usersTests;

import dto.GetRequestBook;
import dto.ValidUserRequest;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

public class GetUserTest extends BaseTest {

    @Test
    public void successGetUser() {
        ValidUserRequest requestBody = ValidUserRequest.builder().userName(fakerLogin).password("Qq1234!!").build();

        String userId =
                postRequestUsers(endpoint, 201, requestBody)
                .body().jsonPath().getString("userID");
        System.out.println("++userId++ : " + userId);

        String token =
                postRequestUsers(endpointGenerateToken, 200, requestBody)
                        .body().jsonPath().getString("token");
        System.out.println("++token++ : " + token);

        postRequestUsers(endpointAuthorized, 200, requestBody);

        GetRequestBook getRequestBook = GetRequestBook.builder().userId(userId).build();
        //getRequestWithToken(endpointGetUser, 401,token);
     getRequestWithToken(endpoint+"/"+userId, 200,token, getRequestBook);
       // getRequestWithToken(endpoint+"?User="+userId, 200,token);

    }
}
