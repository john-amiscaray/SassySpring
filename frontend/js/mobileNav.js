function navToggle() {
    let mobileNav = document.getElementById("navMobile");
    if (mobileNav.style.display === "none" || mobileNav.style.display === "") {
        mobileNav.style.display = "block";
    }
    else {
        mobileNav.style.display = "none";
    }
}
