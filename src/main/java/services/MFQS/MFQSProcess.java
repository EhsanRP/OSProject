package services.MFQS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MFQSProcess {
    char name;
    int AT, BT, WT, TAT, RT, CT;
}
