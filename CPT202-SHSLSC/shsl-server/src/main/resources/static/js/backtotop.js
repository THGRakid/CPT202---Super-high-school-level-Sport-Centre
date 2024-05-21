window.onload = function() {
    var scrollToTopBtn = document.getElementById("scrollToTopBtn");
    scrollToTopBtn.addEventListener("click", function() {
        // 使用smooth滚动到页面顶部
        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });
    });
};
