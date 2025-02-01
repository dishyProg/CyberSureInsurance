function updatePrice() {
    var type = document.getElementById("insuranceType").value;
    var priceField = document.getElementById("insurancePrice");

    switch (type) {
        case "Basic AugmentShield":
            priceField.value = "49";
            break;
        case "Elite AugmentGuard":
            priceField.value = "99";
            break;
        case "Ultimate CyberGuard":
            priceField.value = "199";
            break;
        case "Basic CyberShield":
            priceField.value = "14";
            break;
        case "Elite DataGuard":
            priceField.value = "39";
            break;
        case "Ultimate DataVault":
            priceField.value = "79";
            break;
        case "Basic Glide":
            priceField.value = "29";
            break;
        case "Elite TravelGuard":
            priceField.value = "69";
            break;
        case "Ultimate CyberGlide":
            priceField.value = "129";
            break;
        case "Basic DriveShield":
            priceField.value = "19";
            break;
        case "Elite CyberDrive":
            priceField.value = "49";
            break;
        case "Ultimate NeonGuard":
            priceField.value = "99";
            break;
        case "Basic Shield":
            priceField.value = "19";
            break;
        case "Elite Guard":
            priceField.value = "49";
            break;
        case "Ultimate NeuroShield":
            priceField.value = "99";
            break;
        default:
            priceField.value = "0";
    }
}


window.onload = updatePrice;