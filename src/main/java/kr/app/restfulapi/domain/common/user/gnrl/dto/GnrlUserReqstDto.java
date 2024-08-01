package kr.app.restfulapi.domain.common.user.gnrl.dto;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;
import kr.app.restfulapi.domain.common.user.gnrl.entity.UserAuthrt;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserType;

public record GnrlUserReqstDto(
    // @Size(min = 13, max = 13, message = "업체식별번호는 정확히 {max}자 입니다.")
    String bzentyTsid,
    @NotBlank(message = "로그인아이디는 필수 입력값입니다.") String lgnId,
    @NotBlank(message = "사용자명은 필수 입력값입니다.") String userNm,
    String cardNo,
    @NotBlank(message = "업체관리자여부는 필수 입력값입니다.")
    @Size(max = 1, message = "업체관리자여부는 {max}자 입니다.")
    @Pattern(regexp = "[YN]", message = "업체관리자여부는 Y 또는 N 이어야 합니다.") String bzentyMngrYn,
    @NotBlank(message = "비밀번호는 필수 입력값입니다.") String pswd,
    String mbtlnum,
    String emlAddr,
    String telno,
    String fxno,
    @NotEmpty(message = "사용자유형은 1개 이상 선택해야 합니다.") List<UserType> userTypes) {

  public GnrlUser toEntity() {
    return GnrlUser.builder()
        .bzentyTsid(bzentyTsid)
        .lgnId(lgnId)
        .userNm(userNm)
        .cardNo(cardNo)
        .bzentyMngrYn(bzentyMngrYn)
        .pswd(pswd)
        .mbtlnum(mbtlnum)
        .emlAddr(emlAddr)
        .telno(telno)
        .fxno(fxno)
        .build();
  }

  public List<UserAuthrt> toUserAuthrtEntities(GnrlUser gnrlUser) {
    return userTypes.stream()
        .map(userType -> UserAuthrt.builder().userTsid(gnrlUser.getUserTsid()).gnrlUser(gnrlUser).userTypeCd(userType).build())
        .collect(Collectors.toList());
  }
}
