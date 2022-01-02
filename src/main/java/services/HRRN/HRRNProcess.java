package services.HRRN;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HRRNProcess {
    char name;
    int at;
    int bt;
    int ct;
    int wt;
    int tt;
    boolean completed;
    float ntt;
}
