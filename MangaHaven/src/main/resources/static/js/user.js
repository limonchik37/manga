let user;

window.addEventListener("load", () => {
    fetchuser().then(user => {
        console.log('User:', user);
        setUpUserProfileButton(user.id);
    }).catch(error => {
        console.error('Failed to fetch user:', error);
    });
})

function setUpUserProfileButton(userId) {
    document.getElementById('profile-link').href = "/user/" + userId;
}

