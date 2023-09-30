@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(content = @`
    <hq>All users list</hq>
    <table>

    @for(var user : page.getUsers())
        <tr>

        <td><a href="/users/${user.getId()}"> ${user.getId()} </a></td>
        <td>${user.getFirstName()} </td>
        <td>${user.getLastName()} </td>
        <td>${user.getEmail()} </td>
        </tr>
    @endfor

    </table>`
    ,footer = @`<a>heare is a footer</a>`

)
