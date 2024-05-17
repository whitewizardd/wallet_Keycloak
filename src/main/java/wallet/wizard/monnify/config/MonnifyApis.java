package wallet.wizard.monnify.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class MonnifyApis {
    public static final String TRANSFER_URL = "https://sandbox.monnify.com/api/v2/disbursements/single";
    public static final String CREATE_WALLET = "https://sandbox.monnify.com/api/v1/disbursements/wallet";
    public static final String CHECK_BALANCE = "https://sandbox.monnify.com/api/v1/disbursements/wallet/balance?accountNumber=";
    public static final String LOGIN = "https://sandbox.monnify.com/api/v1/auth/login";
    public static final String AUTHORIZE_TRF = "https://sandbox.monnify.com/api/v2/disbursements/single/validate-otp";
}
