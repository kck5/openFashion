package com.bmlmunjal.openfashion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements RecyclerView.OnClickListener{

//    private TextView logoutTextView;
//    private FirebaseAuth mAuth;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private ArrayList<GenderClass> genderArrayList;
    private String[] genderText;
    private int[] genderImage;
    RecyclerView recyclerViewGender;

    private RecyclerView recyclerViewHomeCard;
    private ArrayList<HomeCard> homeCardArrayList;
    private String[] homeCardText1;
    private String[] homeCardText2;
    private String[] homeCardPrice1;
    private String[] homeCardPrice2;
    private int[] homeCardImage1;
    private int[] homeCardImage2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_home,container,false);
//        mAuth = FirebaseAuth.getInstance();
//        logoutTextView=(TextView) myView.findViewById(R.id.logoutTextView);
//        logoutTextView.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();
        recyclerViewGender = view.findViewById(R.id.recyclerViewGenders);
        recyclerViewGender.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewGender.setHasFixedSize(true);
        GenderAdaptorHomeFragment genderAdaptorHomeFragment = new GenderAdaptorHomeFragment(getContext(),genderArrayList);
        recyclerViewGender.setAdapter(genderAdaptorHomeFragment);
        genderAdaptorHomeFragment.notifyDataSetChanged();

        recyclerViewHomeCard = view.findViewById(R.id.recyclerViewCards);
        recyclerViewHomeCard.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerViewHomeCard.setHasFixedSize(true);
        homeCardAdaptorHomeFragment homeFragment= new homeCardAdaptorHomeFragment(getContext(),homeCardArrayList);
        recyclerViewHomeCard.setAdapter(homeFragment);
        homeFragment.notifyDataSetChanged();

    }
    private void dataInitialize(){
        genderArrayList = new ArrayList<>();
        homeCardArrayList = new ArrayList<>();
        genderText= new String[]{
                getString(R.string.mens),
                getString(R.string.kids),
                getString(R.string.women),
                getString(R.string.girls),
                getString(R.string.formals),
                getString(R.string.sports)
        };
        homeCardText1= new String[]{
                getString(R.string.black_hoodie),
                getString(R.string.pink_crew_neck_t_shirt),
                getString(R.string.SlimWomen),
                getString(R.string.WomensDressWithStripes),
        };
        homeCardText2= new String[]{
                getString(R.string.blackCrewNeckTShirt),
                getString(R.string.pinkCrewNeckTShirt),
                getString(R.string.WomensGrayHoodie),
                getString(R.string.WomensPinkCoverOns),
        };
        homeCardPrice1= new String[]{
                getString(R.string.five_hundred),
                getString(R.string.five_hundred),
                getString(R.string.seven_hundred),
                getString(R.string.nine_hundred)
        };

        homeCardPrice2= new String[]{
                getString(R.string.five_hundred),
                getString(R.string.five_hundred),
                getString(R.string.fifteen_hundred),
                getString(R.string.nine_hundred)
        };
        homeCardImage1= new int[]{
                R.drawable.black_t_shirt,
                R.drawable.pink_t_shirt_men,
                R.drawable.slim_fit,
                R.drawable.strips_dress_women,
        };
        homeCardImage2= new int[]{
                R.drawable.black_t_shirt_women,
                R.drawable.pink_t_shirt_women,
                R.drawable.grey_hoodie,
                R.drawable.cover_on_women,
        };
        genderImage= new int[]{
                R.drawable.black,
                R.drawable.kid_on_boarding,
                R.drawable.women_on_boarding,
                R.drawable.women_on_boarding,
                R.drawable.black1,
                R.drawable.black2,
        };
        for(int i=0;i<genderText.length;i++){
            GenderClass genderClass = new GenderClass(genderText[i], genderImage[i]);
            genderArrayList.add(genderClass);
        }
        for(int i=0;i<homeCardText1.length;i++){
            HomeCard homeCard = new HomeCard(homeCardText1[i],homeCardText2[i],homeCardPrice1[i],homeCardPrice2[i],homeCardImage1[i],homeCardImage2[i]);
            homeCardArrayList.add(homeCard);
        }
    }

    private void closeFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onClick(View v) {

    }
}