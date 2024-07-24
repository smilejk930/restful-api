package kr.app.restfulapi.global.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

/**
 * 등록/수정에 대한 생성자와 생성 시간 자동 기록
 * 
 * @MappedSuperclass
 *                   애노테이션은 JPA에서 매핑된 슈퍼클래스임을 나타냅니다.
 *                   매핑된 슈퍼클래스는 그 자체로는 엔티티가 아니며, 단지 그 하위 클래스들에 대한 공통 속성과 매핑을 정의합니다.
 * 
 *                   <p>
 *                   매핑된 슈퍼클래스에 정의된 필드와 매핑 정보는 그 하위 클래스들에게 상속됩니다.
 *                   하지만 매핑된 슈퍼클래스 자체는 데이터베이스 테이블에 매핑되지 않습니다.
 *                   </p>
 */
@Getter
@MappedSuperclass
public abstract class BaseAuditingEntity extends BaseAuditingUpdtEntity {

}
