import { Link, Outlet } from "react-router-dom";
import moment from 'moment';

const SiteLayOut = () => {
    const date = new Date();
    const formattedDate = moment(date).format('MMM DD');

    return (
        <div style={{ textAlign: "center" }}>
            <table
                style={{ width: '800px', marginLeft: "auto", marginRight: "auto" }} border={1}>
                <tr>
                    <td>
                        <h1>{formattedDate}</h1>
                    </td>
                </tr>
                <tr>
                    <td>
                        {/* App과 동일한 경로로 */}
                        <Link to="/">홈</Link>&nbsp;&nbsp;
                        <Link to="/gallery.go">갤러리</Link>&nbsp;&nbsp;
                        <Link to="/notice.go">공지사항</Link>
                    </td>
                </tr>
                <tr>
                    <td>
                        <Outlet />
                        {/* 해당 경로에 매칭된 자식 라우트 렌더링 */}
                    </td>
                </tr>
            </table>
        </div>
    );
};

export default SiteLayOut;
