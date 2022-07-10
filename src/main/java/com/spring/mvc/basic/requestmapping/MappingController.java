package com.spring.mvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

  private Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping({"/hello-basic", "/hello-go"})
  public String helloBasic() {
    log.info("helloBasic");
    return "ok";
  }

  //HTTP 메서드 매핑
  @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
  public String mappingGetV1() {
    log.info("mappingGetV1");
    return "ok";
  }

  //HTTP 메서드 매핑 축약
  @GetMapping(value = "/mapping-get-v2")
  public String mappingGetV2() {
    log.info("mappingGetV2");
    return "ok";
  }

  //PathVariable(경로 변수) 사용
  @GetMapping(value = "/mapping/{userId}")
  public String mappingPath(@PathVariable("userId") String data) {
    log.info("mappingPath userId={}", data);
    return "ok";
  }

  //PathVariable 사용 - 다중
  //@PathVariable 의 이름과 파라미터 이름이 같으면 생략가능
  @GetMapping(value = "/mapping/user/{userId}/orders/{orderId}")
  public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
    log.info("mappingPath userId={}, orderId={}", userId, orderId);
    return "ok";
  }

  // 특정 파라미터 조건 매핑

  /**
   * 파라미터로 추가 매핑
   * params="mode"
   * params="!mode"
   * params="mode=debug"
   * params="mode!=debug" (! = )
   * params = {"mode=debug","data=good"}
   */
  @GetMapping(value = "/mapping-param", params = "mode=debug")
  public String mappingParam() {
    log.info("mappingParam");
    return "ok";
  }

  /**
   * headers="mode",
   * headers="!mode"
   * headers="mode=debug"
   * headers="mode!=debug" (! = )
   */
  @GetMapping(value = "/mapping-header", headers = "mode=debug")
  public String mappingHeader() {
    log.info("mappingHeader");
    return "ok";
  }

  /**
   * Content-Type 헤더 기반 추가 매핑 Media Type
   * consumes="application/json"
   * consumes="!application/json"
   * consumes="application/*"
   * consumes="*\/*"
   * MediaType.APPLICATION_JSON_VALUE
   */
  @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String mappingConsumes() {
    log.info("mappingConsumes");
    return "ok";
  }

  /**
   * Accept 헤더 기반 Media Type
   * produces = "text/html"
   * produces = "!text/html"
   * produces = "text/*"
   * produces = "*\/*"
   */
  @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
  public String mappingProduces() {
    log.info("mappingProduces");
    return "ok";
  }

}
