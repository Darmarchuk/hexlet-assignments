@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`
    <hq>All users list</hq>
    <table>
        <tr>
            <td>${ page.getUser().getEmail()} </td>
        </tr>
        <tr>
            <td>${ page.getUser().getFirstName() } ${ page.getUser().getLastName() }</td>
        </tr>
    </table>`
,footer = @`<a>here is a footer</a>`

)
