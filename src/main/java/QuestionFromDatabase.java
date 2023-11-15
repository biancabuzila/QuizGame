import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionFromDatabase {
    private Game game;
    private JSONObject obj;

    public QuestionFromDatabase(Game game) {
        this.game = game;
    }

    public void setGameQuestion() throws SQLException {
        try {
            Connection con = Database.getConnection();
            CallableStatement cs = con.prepareCall("{? = call urmatoarea_intrebare(p_username => ?, p_raspuns => ?) }");
            cs.registerOutParameter(1, java.sql.Types.VARCHAR);
            cs.setString(2, game.getUsername());
            cs.setString(3, game.getQuestionAnswer());
            cs.execute();
            obj = new JSONObject(cs.getString(1));
            cs.close();

            if (obj.has("Total")) {
                game.setScore(obj.getFloat("Total"));
                PreparedStatement stmt = con.prepareStatement("delete from teste where username=?");
                stmt.setString(1, game.getUsername());
                stmt.executeUpdate();
                stmt.close();
                return;
            }

            game.setIdQuestion(obj.getString("id_intrebare"));
            game.setQuestion(obj.getString("intrebare"));

            List<String> idAnswers = new ArrayList<>();
            idAnswers.add(obj.getString("id_rasp_1"));
            idAnswers.add(obj.getString("id_rasp_2"));
            idAnswers.add(obj.getString("id_rasp_3"));
            idAnswers.add(obj.getString("id_rasp_4"));
            idAnswers.add(obj.getString("id_rasp_5"));
            idAnswers.add(obj.getString("id_rasp_6"));
            game.setIdAnswers(idAnswers);

            List<String> answers = new ArrayList<>();
            answers.add(obj.getString("raspuns_1"));
            answers.add(obj.getString("raspuns_2"));
            answers.add(obj.getString("raspuns_3"));
            answers.add(obj.getString("raspuns_4"));
            answers.add(obj.getString("raspuns_5"));
            answers.add(obj.getString("raspuns_6"));
            game.setAnswers(answers);
        } catch (SQLException e) {
            Database.getConnection().rollback();
        }
    }
}
