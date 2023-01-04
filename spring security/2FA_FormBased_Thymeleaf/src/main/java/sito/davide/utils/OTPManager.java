package sito.davide.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.util.Utils;

@Component 
public class OTPManager  {

    @Autowired
    private SecretGenerator secretGen;

    @Autowired
    private QrGenerator qrGen;

    @Autowired
    private CodeVerifier codeVerifier;

    public String generateSecretKey() {
        return secretGen.generate();
    }

    public String getQRCode(String secret) throws QrGenerationException {
        QrData data = new QrData.Builder().label("MFA")
            .secret(secret)
            .issuer("sito.davide")
            .algorithm(HashingAlgorithm.SHA256)
            .digits(6)
            .period(30)
            .build();
        return Utils.getDataUriForImage(
        			qrGen.generate(data),
        			qrGen.getImageMimeType()
        );
    }

    public boolean verifyOTPCode(String code, String secret) {
        return codeVerifier.isValidCode(secret, code);
    }
}