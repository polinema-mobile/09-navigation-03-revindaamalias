package id.ac.polinema.skor.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import id.ac.polinema.skor.R;
import id.ac.polinema.skor.databinding.FragmentScoreBinding;
import id.ac.polinema.skor.models.GoalScorer;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {

	public static final String HOME_REQUEST_KEY = "home";
	public static final String AWAY_REQUEST_KEY = "away";
	public static final String SCORER_KEY = "scorer";

	private List<GoalScorer> homeGoalScorerList = new ArrayList<>();
	private List<GoalScorer> awayGoalScorerList = new ArrayList<>();

	private FragmentScoreBinding binding;
	String homeSC;
	String awaySC;

	public ScoreFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
//		add new code
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false);
		binding.setHomeGoalScorerList(homeGoalScorerList);
		binding.setAwayGoalScorerList(awayGoalScorerList);
		binding.setFragment(this);

		getParentFragmentManager().setFragmentResultListener(HOME_REQUEST_KEY, this, new FragmentResultListener() {
			@Override
			public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
				GoalScorer goalScorer = result.getParcelable(SCORER_KEY);
				homeGoalScorerList.add(goalScorer);
			}
		});

		getParentFragmentManager().setFragmentResultListener(AWAY_REQUEST_KEY, this, new FragmentResultListener() {
			@Override
			public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
				GoalScorer goalScorer = result.getParcelable(SCORER_KEY);
				awayGoalScorerList.add(goalScorer);
			}
		});
		return binding.getRoot();
	}

	public String home(){
		StringBuilder builder = new StringBuilder();
		for (GoalScorer goalScorer : homeGoalScorerList){
			builder.append(goalScorer.getName())
					.append(" ")
					.append(goalScorer.getMinute())
					.append("\" ");
		}
		return builder.toString();
	}

	public String away(){
		StringBuilder builder = new StringBuilder();
		for (GoalScorer goalScorer : awayGoalScorerList){
			builder.append(goalScorer.getName())
					.append(" ")
					.append(goalScorer.getMinute())
					.append("\" ");
		}
		return builder.toString();
	}

	public void onAddHomeClick(View view) {
		ScoreFragmentDirections.GoalScorerAction action = ScoreFragmentDirections.goalScorerAction(HOME_REQUEST_KEY);
		Navigation.findNavController(view).navigate(action);
	}

	public void onAddAwayClick(View view) {
			ScoreFragmentDirections.GoalScorerAction action = ScoreFragmentDirections.goalScorerAction(AWAY_REQUEST_KEY);
			Navigation.findNavController(view).navigate(action);
	}

}