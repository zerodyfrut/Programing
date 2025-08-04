function Nav(props) {
    const lis = [];

    for (let i = 0; i < props.topics.length; i++) {
        let t = props.topics[i]
        lis.push(<li key={t.id}>{t.title} {t.body}</li>);
        
    }

    return (
        <nav>
            <ol>
                {lis}
            </ol>
        </nav>
    );
}
export default Nav;