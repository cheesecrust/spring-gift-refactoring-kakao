package gift.auth;

import org.springframework.stereotype.Service;

import gift.member.Member;
import gift.member.MemberService;

@Service
public class KakaoAuthService {
    private final KakaoLoginClient kakaoLoginClient;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    public KakaoAuthService(
        KakaoLoginClient kakaoLoginClient,
        MemberService memberService,
        JwtProvider jwtProvider
    ) {
        this.kakaoLoginClient = kakaoLoginClient;
        this.memberService = memberService;
        this.jwtProvider = jwtProvider;
    }

    public TokenResponse processCallback(String code) {
        KakaoLoginClient.KakaoTokenResponse kakaoToken = kakaoLoginClient.requestAccessToken(code);
        KakaoLoginClient.KakaoUserResponse kakaoUser = kakaoLoginClient.requestUserInfo(kakaoToken.accessToken());

        Member member = memberService.findOrCreateByEmail(kakaoUser.email(), kakaoToken.accessToken());

        String token = jwtProvider.createToken(member.getEmail());
        return new TokenResponse(token);
    }
}
