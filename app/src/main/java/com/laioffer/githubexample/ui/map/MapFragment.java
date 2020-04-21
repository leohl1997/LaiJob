package com.laioffer.githubexample.ui.map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.CustomMapInfoWindowBinding;
import com.laioffer.githubexample.databinding.MapFragmentBinding;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFragment extends BaseFragment<MapViewModel, MapRepository>
        implements OnMapReadyCallback {

    class CustomInfoPageAdapter implements GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

        private Map<Marker, Job> map = new HashMap<>();
        private Context fragment;

        public CustomInfoPageAdapter(Context fragment) {
            this.fragment = fragment;
        }

        public void addMarker(Marker marker, Job job) {
            map.put(marker, job);
        }

        private Job getJob(Marker marker) {
            if (map.get(marker) != null) {
                return map.get(marker);
            } else {
                for (Marker savedMaker : map.keySet()) {
                    if (savedMaker.getSnippet().equals(marker.getSnippet())) {
                        return map.get(savedMaker);
                    }
                }
            }
            return null;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            Job currJob = getJob(marker);
            if (currJob == null) {
                return null;
            }
            View view = getLayoutInflater().inflate(R.layout.custom_map_info_window, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_title);
            textView.setText(currJob.name);
            textView =  (TextView) view.findViewById(R.id.tv_company_name);
            textView.setText(currJob.company);
            textView =  (TextView) view.findViewById(R.id.tv_location);
            textView.setText(currJob.address);
            ImageView imageView = (ImageView) view.findViewById(R.id.img_info);
//            CustomMapInfoWindowBinding binding = CustomMapInfoWindowBinding.inflate(getLayoutInflater());
//            binding.tvTitle.setText(currJob.name);
//            binding.tvCompanyName.setText(currJob.company);
//            binding.tvLocation.setText(currJob.address);
            if (!currJob.imageUrl.isEmpty()) {
                Picasso.get().setLoggingEnabled(true);
                Picasso.get().load(currJob.imageUrl).placeholder(R.drawable.ic_center).resize(100,100) .into(imageView);
                //Glide.with(view).load(currJob.imageUrl).into(imageView);
            }
//            return binding.getRoot();
            return view;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }

        @Override
        public void onInfoWindowClick(Marker marker) {
            Job currJob = getJob(marker);
            if (currJob == null) {
                return;
            }
            Utils.constructToast(getContext(), currJob.name).show();
        }
    }

    private MapFragmentBinding binding;
    private MapView mapView;
    private GoogleMap googleMap;
    private NavigationManager navigationManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  MapFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.event_map_view);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
        FloatingActionButton fab = view.findViewById(R.id.fab_return);
        fab.setOnClickListener( v -> {
            navigationManager.navigateTo(new HomeListFragment());
        });
        FloatingActionButton returnFab = view.findViewById(R.id.fab_center);
        returnFab.setOnClickListener( v -> {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(Config.lat, Config.lon))
                    .zoom(10)
                    .build();

            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        });
        viewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), list -> {
//            ArrayList<Marker> markerList = new ArrayList<>();
            if (list == null) {
                Utils.constructToast(getContext(), "Null List!").show();
                return;
            }
            CustomInfoPageAdapter adapter = new CustomInfoPageAdapter(getContext());
            for (Job job : list) {
                LatLng position = new LatLng(job.location.latitude, job.location.longitude);
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(position)
                        .title(job.name)
                        .snippet(job.itemId)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                googleMap.addMarker(markerOptions);
                Marker marker = googleMap.addMarker(markerOptions);
                adapter.addMarker(marker, job);
                //markerList.add(marker);
            }
            googleMap.setInfoWindowAdapter(adapter);
            googleMap.setOnInfoWindowClickListener(adapter);
//            googleMap.setOnInfoWindowClickListener(marker -> {
//                for (int i = 0; i < markerList.size(); i++) {
//                    if (marker.getTitle().equals(markerList.get(i).getTitle())) {
//                        Utils.constructToast(getContext(), list.get(i).address).show();
//                    }
//                }
//            });
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        this.googleMap = googleMap;
        this.googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style_json));

        LatLng position = new LatLng(Config.lat, Config.lon);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(10)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MarkerOptions markerOptions = new MarkerOptions().position(position).title("Me");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        googleMap.addMarker(markerOptions);
    }

    @Override
    protected MapViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(MapViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new MapViewModel(getRepository());
            }
        };
    }

    @Override
    protected MapRepository getRepository() {
        return new MapRepository();
    }

}
