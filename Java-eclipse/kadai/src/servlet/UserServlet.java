package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.UserDao;
import exception.UserNotFoundException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GETリクエスト処理（画面表示用）
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String forwardPath = null;

        try {
            if ("select".equals(action)) {
                // 処理選択画面へ
                forwardPath = "select.jsp";

            } else if ("disp".equals(action)) {
                // 表示画面へ（UserDaoから全件取得）
                UserDao dao = new UserDao();
                List<User> list = dao.findAll();
                request.setAttribute("userList", list);
                forwardPath = "disp.jsp";

            } else if ("update".equals(action)) {
                // 更新検索画面へ
                forwardPath = "updateSearch.jsp";

            } else if ("delete".equals(action)) {
                // 削除検索画面へ
                forwardPath = "deleteSearch.jsp";
            }

        } catch (UserNotFoundException e) {
            // データ未存在時のエラー画面へ
            request.setAttribute("error", e.getMessage());
            if ("disp".equals(action)) {
                forwardPath = "dispError.jsp";
            } else if ("update".equals(action)) {
                forwardPath = "updateError.jsp";
            } else if ("delete".equals(action)) {
                forwardPath = "deleteError.jsp";
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // JSPへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
        dispatcher.forward(request, response);
    }

    // POSTリクエスト処理（ボタン押下時）
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String forwardPath = null;

        try {
            UserDao dao = new UserDao();

            if ("disp".equals(action)) {
                // 表示処理（全件取得 → disp.jsp）
                List<User> list = dao.findAll();
                request.setAttribute("userList", list);
                forwardPath = "disp.jsp";

            } else if ("updateForm".equals(action)) {
                // id指定で検索 → 更新画面へ
                int id = Integer.parseInt(request.getParameter("id"));
                User user = dao.findById(id);
                request.setAttribute("user", user);
                forwardPath = "updateForm.jsp";

            } else if ("updateExecute".equals(action)) {
                // 更新処理
                int id = Integer.parseInt(request.getParameter("id"));
                String fName = request.getParameter("fName");
                String gName = request.getParameter("gName");
                String fName_kana = request.getParameter("fName_kana");
                String gName_kana = request.getParameter("gName_kana");
                int birth_year = Integer.parseInt(request.getParameter("birth_year"));
                int birth_month = Integer.parseInt(request.getParameter("birth_month"));
                int birth_day = Integer.parseInt(request.getParameter("birth_day"));

                User user = new User(id, fName, gName, fName_kana, gName_kana, birth_year, birth_month, birth_day);
                dao.update(user);
                forwardPath = "updateSuccess.jsp";

            } else if ("deleteExecute".equals(action)) {
                // 削除処理
                int id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                forwardPath = "deleteSuccess.jsp";
            }

        } catch (UserNotFoundException e) {
            // id未存在時のエラー画面へ
            request.setAttribute("error", e.getMessage());
            if ("updateForm".equals(action) || "updateExecute".equals(action)) {
                forwardPath = "updateError.jsp";
            } else if ("deleteExecute".equals(action)) {
                forwardPath = "deleteError.jsp";
            } else if ("disp".equals(action)) {
                forwardPath = "dispError.jsp";
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // JSPへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
        dispatcher.forward(request, response);
    }
}