import './Footer.css'

const Footer = () => {

    const user = { name: "김솔데", isLogin: "true" };

    if (user.isLogin) {
        return <div className="logout">로그아웃</div>
    } else {
        return <div >로그인</div>
        // return (
        //     <footer>
        // <h1>Footer</h1>
        //     </footer>
        // );
    }
}

export default Footer;