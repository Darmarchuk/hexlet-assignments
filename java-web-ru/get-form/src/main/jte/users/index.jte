@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(content  = @`

    <form action="/users" method="get">
    <input type="search" name="term" value="${page.getFilter()}" />
    <input type="submit" value="Search" />
    </form>
    @if(!page.getUsers().isEmpty())
        <table>
            @for(var user : page.getUsers())
                <tr>
                    <td><a href="/users/${user.getId()}"> ${user.getId()} </a></td>
                    <td>${user.getFirstName()} ${user.getLastName()}</td>
                    <td>${user.getEmail()}</td>
                </tr>
            @endfor

        </table>
    @endif
`)

