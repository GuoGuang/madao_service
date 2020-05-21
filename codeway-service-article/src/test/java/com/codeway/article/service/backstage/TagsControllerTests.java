package com.codeway.article.service.backstage;

import com.codeway.utils.JsonData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TagsControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getTags() {
        ResponseEntity<JsonData> responseEntity = this.testRestTemplate.getForEntity("/tags", JsonData.class, "prius");
        JsonData body = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.getCode()).isEqualTo("200");
        assertThat(body.getData()).isEqualTo("hybrid");
    }

}