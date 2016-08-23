package layout;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.learn.howtodraw.draw.BaseFragment;
import com.learn.howtodraw.draw.BookActivity;
import com.learn.howtodraw.draw.CustomGrid;
import com.learn.howtodraw.draw.ExpandableGridView;
import com.learn.howtodraw.draw.PageActivity;
import com.learn.howtodraw.draw.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThirdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BrowseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(int param1, int param2, int param3) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putInt("cardText1Id", param1);
        args.putInt("bookPageIds", param2);
        args.putInt("cardImageId", param3);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_third, container, false);
        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //send content in the grid
        final String[] cardText1 = getResources().getStringArray(getArguments().getInt("cardText1Id"));
        final String[] bookPageIds = getResources().getStringArray(getArguments().getInt("bookPageIds"));
        final TypedArray cardImageDrawables = getResources().obtainTypedArray(getArguments().getInt("cardImageId"));
        final int[] cardImage = new int[cardImageDrawables.length()];
        for (int i = 0; i < cardImageDrawables.length(); i++)
            cardImage[i] = cardImageDrawables.getResourceId(i, 0);
        final CustomGrid adapter = new CustomGrid(getActivity(), cardText1, cardImage, bookPageIds);
        //set the grid
        ExpandableGridView gridView = (ExpandableGridView) view.findViewById(R.id.gridthird);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);
        gridView.setFocusable(false);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pageId = (String) adapter.getItem(position);

                int slidesID = getResources().getIdentifier(pageId + "Slides", "array", getClass().getPackage().getName());
                // showToast(20000, cardText1[+position] );
                // Toast.makeText(BookActivity.this, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(),PageActivity.class);
                Log.d("hello" + slidesID, "" );
                intent.putExtra("bookSlides", slidesID);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener2 = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
