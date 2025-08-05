// const Button = (props) => {
//     console.log(props)

//     return (
//         <button style={{ color: props.color }}>{props.text} - {props.color.toUpperCase()}</button>
//     );

// Button.defaultProps={
//     color : "black"
// }

const Button = ({text, color='black',children}) => {
    
    return <button style={{ color: color }}>
        {text} - {color.toUpperCase()}
        {children}
        </button>

}

export default Button;