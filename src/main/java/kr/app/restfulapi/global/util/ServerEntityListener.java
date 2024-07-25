package kr.app.restfulapi.global.util;

import org.springframework.stereotype.Component;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import kr.app.restfulapi.global.config.ServerConfig;
import kr.app.restfulapi.global.entity.BaseServerEntity;

@Component
public class ServerEntityListener {

  private final ServerConfig serverConfig;

  public ServerEntityListener(ServerConfig serverConfig) {
    this.serverConfig = serverConfig;
  }

  @PrePersist // 등록 시
  public void prePersist(BaseServerEntity entity) {
    if (entity.getRegistServerNm() == null) {
      entity.setRegistServerNm(serverConfig.getName());
    }
  }

  @PreUpdate // 수정 시
  public void preUpdate(BaseServerEntity entity) {
    entity.setLastServerNm(serverConfig.getName());
  }
}
