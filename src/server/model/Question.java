package server.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class Question {
	
	private int id;
	private int level;
	private String question;
	private ArrayList<String> answer;
	private int trueAnswer;
	
	public Question(int id, int level, String question, ArrayList<String> answer) {
		super();
		this.id = id;
		this.level = level;
		this.question = question;
		this.answer = answer;
	}
	
	public static boolean insert(Question question) {
		try {            
			Connection conn = DBConnection.getConn();
            String sql = "insert into questions(level,question,a1,a2,a3,a4,at) values('"+question.getLevel()+"','"+question.getQuestion()+"','"+question.getAnswer().get(0)+"','"+question.getAnswer().get(1)+"','"+question.getAnswer().get(2)+"','"+question.getAnswer().get(3)+"','"+question.getTrueAnswer()+"')";      
            Statement st = (Statement) conn.createStatement();
            int rs =  st.executeUpdate(sql);
            return rs>0;            
        } 
        catch (SQLException ex) 
        {
        	System.out.println("Insert question error!");
        }
		return false;
	}
	
	public static ArrayList<Question> select() {
		return select(new Hashtable<String, String>());
	}
	
	public static ArrayList<Question> select(Hashtable<String, String> hm) {
		Connection conn = DBConnection.getConn();
		String sql = "select * from questions";
		boolean flag = false;
		for(Map.Entry<String, String> e : hm.entrySet()) {
			if(!flag) {
				flag = true;
				sql += " where " + e.getKey() + "='" + e.getValue() + "'";
			}
			else {
				sql += " and " + e.getKey() + "='" + e.getValue() + "'";
			}
		}
		//System.out.println(sql);
		ArrayList<Question> result = new ArrayList<Question>();
		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);			
			while(rs.next()) {
				ArrayList<String> answers = new ArrayList<String>();
				answers.add(rs.getString("a1"));
				answers.add(rs.getString("a2"));
				answers.add(rs.getString("a3"));
				answers.add(rs.getString("a4"));
				Question q = new Question(rs.getInt("id"), rs.getInt("level"), rs.getString("question"), answers);
				q.setTrueAnswer(rs.getInt("at"));
				result.add(q);
			}
		}
		catch (SQLException e) {
			System.out.println("Select users error!");
		}	
		return result;
	}
	
	public String getQuestion() {
		return question;
	}
	public ArrayList<String> getAnswer() {
		return answer;
	}
	public int getTrueAnswer() {
		return trueAnswer;
	}
	public int getLevel() {
		return level;
	}
	public void setTrueAnswer(int trueAnswer) {
		this.trueAnswer = trueAnswer;
	}
	
	public static Question getRandomQuestionByLevel(int level) {
		ArrayList<Question> questions = select(new Hashtable<String, String>(){{
			put("level", String.valueOf(level));
		}});
		Random rand = new Random();
		return questions.get(rand.nextInt(questions.size()));
	}
	
	private static void inputQuestions() {
		ArrayList<Question> questions = new ArrayList<Question>();
		File inputFile = new File("questions.txt");
		if (inputFile.exists()) {
			try {
				Scanner myReader = new Scanner(inputFile);
				int level = 1;
				while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        int n = Integer.valueOf(data);
			        for(int i = 0; i <n; i++) {
			        	String q = myReader.nextLine();
			        	String a1 = myReader.nextLine();
			        	String a2 = myReader.nextLine();
			        	String a3 = myReader.nextLine();
			        	String a4 = myReader.nextLine();
			        	ArrayList<String> answer = new ArrayList<String>();
			        	answer.add(getMainText(a1));
			        	answer.add(getMainText(a2));
			        	answer.add(getMainText(a3));
			        	answer.add(getMainText(a4));
			        	Question question = new Question(0, level, getMainText(q), answer);
			        	questions.add(question);
			        }
			        for(int i = questions.size() - n; i <questions.size(); i++) {
			        	String ta = getMainText(myReader.nextLine());			        	
			        	int index = 0;
			        	if(ta.equals("B")) {
			        		index = 1;
			        	}
			        	if(ta.equals("C")) {
			        		index = 2;
			        	}
			        	if(ta.equals("D")) {
			        		index = 3;
			        	}
			        	//System.out.println("cau " + i + ": " + ta + " - " + index);
			        	questions.get(i).setTrueAnswer(index);
			        }
			        level++;
			      }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Question question : questions) {
				insert(question);
				System.out.println("level:" + question.getLevel());
				System.out.println(question.getQuestion());
				System.out.println(question.getAnswer());
				System.out.println(question.getTrueAnswer());
			}
			
		} else {
			System.out.println("The file does not exist.");
		}
	}
	
	public static void main(String[] args) {
		// inputQuestions();
//		Question question = getRandomQuestionByLevel(1);
//		System.out.println("level:" + question.getLevel());
//		System.out.println(question.getQuestion());
//		System.out.println(question.getAnswer());
//		System.out.println(question.getTrueAnswer());
	}
	
	private static String getMainText(String text) {
		try {
			String[] array = text.split("\t");
			return array[1];
		}
		catch (Exception e) {
			return null;
		}
	}	

	
	
}
