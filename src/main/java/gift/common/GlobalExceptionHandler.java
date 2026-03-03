package gift.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import gift.auth.KakaoAuthController;
import gift.category.Category;
import gift.category.CategoryController;
import gift.member.AdminMemberController;
import gift.member.MemberController;
import gift.option.OptionController;
import gift.order.OrderController;
import gift.product.ProductController;
import gift.wish.WishController;

@RestControllerAdvice(assignableTypes = {ProductController.class, OptionController.class, MemberController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
