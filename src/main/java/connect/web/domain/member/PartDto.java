package connect.web.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 김동혁(잠깐 필요해서 쓴거라 지우셔도 댑니당ㅎㅎ)
@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class PartDto {
    private int part_no;
    private String part_name;
}
